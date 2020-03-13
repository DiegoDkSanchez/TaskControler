package com.example.taskcontroler.presentation.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.VisibleForTesting
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

/**
 * BaseFragment abstract class.
 * All presentation fragments should extend this class in order to inherit
 * basic functionality and architecture format.
 *
 * @param layoutId Generated Int ID that represents a layout xml in Resources.
 * @param viewModelId Generated Int ID that represents the errorViewModel variable
 * in the generated binding classes. Use always BR.errorViewModel.
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<T : ViewDataBinding>(
    @LayoutRes
    private val layoutId: Int,
    private val  viewModelId: Int
): Fragment() {

    lateinit var binding: T
    abstract val viewModel: BaseViewModel?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            layoutId,
            container,
            false
        ) as T
        return binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel?.let { setVariable(viewModelId, it) }
        }.root
    }

    fun intentObserver() = Observer<Event<IntentEvent>> {
        it.getContentIfNotHandled()?.let { intentEvent ->
            startActivity((Intent(requireContext(), intentEvent.clazz)))
            if(intentEvent.finishCurrent){
                requireActivity().finish()
            }
        }
    }

    fun loadFragment(idContainter: Int, fragment: Fragment){
        childFragmentManager.beginTransaction().replace(
            idContainter,
            fragment
        ).commitNow()
    }
}