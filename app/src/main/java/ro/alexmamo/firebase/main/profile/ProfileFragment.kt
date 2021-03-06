package ro.alexmamo.firebase.main.profile

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ro.alexmamo.firebase.adapters.ProductsAdapter
import ro.alexmamo.firebase.adapters.ProductsAdapter.OnProductClickListener
import ro.alexmamo.firebase.base.BaseFragment
import ro.alexmamo.firebase.data.Product
import ro.alexmamo.firebase.data.Response.*
import ro.alexmamo.firebase.data.User
import ro.alexmamo.firebase.databinding.FragmentProfileBinding
import ro.alexmamo.firebase.utils.Actions.Companion.print
import ro.alexmamo.firebase.utils.Constants.PRODUCTS
import ro.alexmamo.firebase.utils.Constants.PRODUCT_NAME

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class ProfileFragment: BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate), OnProductClickListener {
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setProductsAdapter()
        getUser()
    }

    private fun setProductsAdapter() {
        dataBinding.productsRecyclerView.adapter = ProductsAdapter(PRODUCTS, this)
    }

    private fun getUser() {
        viewModel.getUser().observe(viewLifecycleOwner, { response ->
            when(response) {
                is Loading -> dataBinding.progressBar.show()
                is Success -> {
                    setUserDataToViews(response.data)
                    dataBinding.progressBar.hide()
                }
                is Failure -> {
                    print(response.errorMessage)
                    dataBinding.progressBar.hide()
                }
            }
        })
    }

    private fun setUserDataToViews(user: User) {
        with(dataBinding) {
            Glide.with(requireContext())
                .load(user.photoUrl)
                .circleCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(profilePhotoImageView)
            nameTextView.text = user.name
        }
    }

    override fun onProductClick(product: Product) {
        navigateToProductFragment(product.fragment, product.name)
    }

    private fun navigateToProductFragment(productFragment: Int, productName: String) {
        findNavController().navigate(productFragment, bundleOf(PRODUCT_NAME to productName))
    }
}