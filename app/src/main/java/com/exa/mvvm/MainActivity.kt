package com.exa.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import com.exa.mvvm.vm.ArticleViewModel

class MainActivity : AppCompatActivity() {
    private var text:TextView?=null
    lateinit var viewModel: ArticleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text=findViewById(R.id.text)
        viewModel= ArticleViewModel()
        viewModel.fetch(1) //请求数据

        viewModel.articleListData.observe(this, Observer { list ->
            //articleListData 的值改变时触发此监听
          text?.text=list[1].title
        })
        viewModel.errorMsg.observe(this, Observer {
            if (it!=null){

            }
        })
    }
}