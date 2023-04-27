package com.example.demorxjava

import io.reactivex.rxjava3.subjects.BehaviorSubject

class AndroidBus {
    companion object {
        val behaviorSubject = BehaviorSubject.create<Int>()

        fun updateProgress(progress: Int) {
            behaviorSubject.onNext(progress)
        }
    }
}