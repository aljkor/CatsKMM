//
//  FlowWrapper.swift
//  iosApp
//
//  Created by Aljosa Koren on 09/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

class FlowWrapper<T>(private val source: Flow<T>) : Flow<T> by source {
    init {
        freeze()
    }

    fun collect(onEach: (T) -> Unit, onCompletion: (cause: Throwable?) -> Unit): Cancellable {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

        scope.launch {
            try {
                collect {
                    onEach(it)
                }
                onCompletion(null)
            } catch (e: Throwable) {
                onCompletion(e)
            }
        }

        return object : Cancellable {
            override fun cancel() {
                scope.cancel()
            }
        }
    }
}

