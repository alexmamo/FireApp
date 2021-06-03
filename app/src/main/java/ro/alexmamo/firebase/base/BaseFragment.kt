package ro.alexmamo.firebase.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB: ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {
    private var _dataBinding: VB? = null
    val dataBinding get() = _dataBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _dataBinding = inflate.invoke(inflater, container, false)
        return dataBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _dataBinding = null
    }
}