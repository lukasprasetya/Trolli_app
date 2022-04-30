package com.lupa.trolli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.lupa.trolli.features.screen.homepage.HomePageViewModel
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val tvLog: TextView by lazy { findViewById(R.id.txt_log) }
    private val disposables = CompositeDisposable()

    /*private val homePageViewModel: HomePageViewModel by lazy {
        ViewModelProvider(this)[HomePageViewModel::class.java]
    }*/

    private val homePageViewModel:HomePageViewModel by viewModel<HomePageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvLog.setOnClickListener {
            homePageViewModel.getUser()
        }

        /*val userManager = homePageViewModel.userManager //base paging misalnya*/
        val userManager = homePageViewModel.userManager
        userManager.onLoading = {
            tvLog.text = "loading....."
        }
        userManager.onSuccess = { user ->
            tvLog.text = user.fullName
        }
        userManager.onFailure = { code, throwable ->
            tvLog.text = throwable.localizedMessage
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}

/* val service = WebServices.create()
 tvLog.setOnClickListener {
     val userDisposable = service.getUser()

         .subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())
         .doOnSubscribe {
             println("loading......")
             tvLog.text = "Loadinggg"
         }
         .doOnError {
             //ini error retrofit -> misal timeout
             println("error anjay -> ${it.localizedMessage}")
             it.printStackTrace()
             if (it is HttpException) {
                 val message = it.message()
                 println("error anuan -> $message")
                 tvLog.text = message
             }
         }
         .subscribe({

             val isSuccess = it.isSuccessful
             if (isSuccess) {
                 val response = it.body()
                 val data = response?.data
                 tvLog.text = data?.fullName
             } else {
                 // ini error dari response
                 val errorMessage = it.errorBody()
                 tvLog.text = errorMessage?.string()
             }
             println("response -> ${it.body()}")
         }, {

             //ini error dari retrofit juga tapi ada hubungannya sama rx
             tvLog.text = it.localizedMessage
             it.printStackTrace()
         })

     disposable.add(userDisposable)
 }
}

override fun onDestroy() {
 super.onDestroy()
 disposable.dispose()
}
}*/