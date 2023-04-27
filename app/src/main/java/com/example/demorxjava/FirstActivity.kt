package com.example.demorxjava

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.demorxjava.databinding.ActivityFirstBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

class FirstActivity : AppCompatActivity() {
    private var disposable: Disposable? = null
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityFirstBinding = ActivityFirstBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        AndroidBus.behaviorSubject.throttleFirst(1L, TimeUnit.SECONDS)
            .subscribe {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            }
        binding.buttonInFirst.setOnClickListener {
            PassProgressValue()


        }
    }

    fun PassProgressValue() {
        disposable = Observable.range(0, 51)
            .subscribe { progress ->
                Log.e("najeia", progress.toString())
                AndroidBus.updateProgress(progress)
            }
    }


    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

}