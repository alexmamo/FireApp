package ro.alexmamo.firebase.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ro.alexmamo.firebase.BR
import ro.alexmamo.firebase.data.Product
import ro.alexmamo.firebase.databinding.ProductDataBinding

class ProductsAdapter(
    val products: List<Product>,
    private val onProductClickListener: OnProductClickListener
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = ProductDataBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bindProduct(product)
    }

    override fun getItemCount() = products.size

    inner class ProductViewHolder(
        private val dataBinding: ViewDataBinding
    ): RecyclerView.ViewHolder(dataBinding.root) {
        fun bindProduct(product: Product) {
            with(dataBinding) {
                setVariable(BR.product, product)
                setVariable(BR.onProductClickListener, onProductClickListener)
            }
        }
    }

    interface OnProductClickListener {
        fun onProductClick(product: Product)
    }
}