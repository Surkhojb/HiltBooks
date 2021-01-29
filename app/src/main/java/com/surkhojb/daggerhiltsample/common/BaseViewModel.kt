package com.surkhojb.daggerhiltsample.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

open class BaseViewModel: ViewModel(), CoroutineScope {
    private var job: Job;

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        job = SupervisorJob()
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}