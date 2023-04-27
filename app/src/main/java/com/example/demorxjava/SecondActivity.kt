package com.example.demorxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demorxjava.databinding.ActivitySecondBinding
import io.reactivex.rxjava3.disposables.Disposable

class SecondActivity : AppCompatActivity() {
    private lateinit var disposable: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySecondBinding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        disposable = AndroidBus.behaviorSubject
            .subscribe { progress ->
                binding.progressBar.progress = progress
                binding.valueTextView.text = progress.toString()
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}