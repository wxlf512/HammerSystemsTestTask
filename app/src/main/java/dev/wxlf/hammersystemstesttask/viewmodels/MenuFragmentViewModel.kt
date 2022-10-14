package dev.wxlf.hammersystemstesttask.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.wxlf.hammersystemstesttask.data.model.ProductsListResponse
import dev.wxlf.hammersystemstesttask.data.repository.ProductsListRepository
import dev.wxlf.scheduler.extensions.default
import dev.wxlf.scheduler.extensions.set
import kotlinx.coroutines.*
import javax.inject.Inject

sealed class State {
    class ErrorState<T>(val message: T) : State()
    class DefaultState : State()
    class LoadedState(val productsList: ProductsListResponse) : State()
}

@HiltViewModel
class MenuFragmentViewModel @Inject constructor(
    private val productsListRepository: ProductsListRepository
) : ViewModel() {
    val state = MutableLiveData<State>().default(initialValue = State.DefaultState())

    @OptIn(DelicateCoroutinesApi::class)
    fun getProducts() {
        var productsList: ProductsListResponse
        CoroutineScope(Dispatchers.IO).launch {
            productsList = productsListRepository.fetchQuestList()
            withContext(Dispatchers.Main) {
                state.set(newValue = State.LoadedState(productsList))
            }
        }
    }
}