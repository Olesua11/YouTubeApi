package com.example.youtubeapi.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB: ViewBinding, VM: ViewModel>(
    private val inflate: Inflate<VB>,
): Fragment() {

    protected abstract val viewModel: VM
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        observe()
    }

    open fun init(){}
    open fun observe(){}

    protected fun <T> LiveData<Resource<T>>.stataHandler(
        success:(data: T) -> Unit,
        state:((res: Resource<T>) -> Unit)
    ){
        this@stataHandler.observe(viewLifecycleOwner){ res ->
            state(res)
            when(res){
                is Resource.Error -> {
                    Log.e("Resource.Error", "${res.massage}")
                }
                is Resource.Loading -> {}
                is Resource.Success -> {
                    res.data?.let { success.invoke(it) }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}