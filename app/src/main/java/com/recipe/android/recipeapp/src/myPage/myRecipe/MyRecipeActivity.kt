package com.recipe.android.recipeapp.src.myPage.myRecipe

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.recipe.android.recipeapp.config.BaseActivity
import com.recipe.android.recipeapp.databinding.ActivityMyRecipeBinding
import com.recipe.android.recipeapp.src.myPage.myRecipe.`interface`.MyRecipeActivityView
import com.recipe.android.recipeapp.src.myPage.myRecipe.adpater.MyRecipeRecyclerViewAdapter
import com.recipe.android.recipeapp.src.myPage.myRecipe.models.MyRecipeResponse
import com.recipe.android.recipeapp.src.myPage.myRecipe.models.MyRecipeResult
import com.recipe.android.recipeapp.src.myPage.myRecipe.myRecipeCreate.MyRecipeCreateActivity

class MyRecipeActivity: BaseActivity<ActivityMyRecipeBinding>(ActivityMyRecipeBinding::inflate), MyRecipeActivityView {

    val myRecipeRecyclerViewAdapter = MyRecipeRecyclerViewAdapter()
    var myRecipeList = ArrayList<MyRecipeResult>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 리사이클러뷰 바인딩
        binding.rvMyRecipe.apply {
            adapter = myRecipeRecyclerViewAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
        // 나만의 레시피 조회 api 호출
        MyRecipeService(this).getMyRecipe(2,0)

        binding.btnRecipeCreate.setOnClickListener { view ->
            val intent = Intent(this, MyRecipeCreateActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onGetMyRecipeSuccess(response: MyRecipeResponse) {
        response.result.forEach {
            myRecipeList.add(it)
        }

        myRecipeRecyclerViewAdapter.submitList(myRecipeList)
    }

    override fun onGetMyRecipeFailure(message: String) {
        TODO("Not yet implemented")
    }
}