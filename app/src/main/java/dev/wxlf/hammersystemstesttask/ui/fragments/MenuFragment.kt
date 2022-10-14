package dev.wxlf.hammersystemstesttask.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import dev.wxlf.hammersystemstesttask.R
import dev.wxlf.hammersystemstesttask.adapters.*
import dev.wxlf.hammersystemstesttask.viewmodels.MenuFragmentViewModel
import dev.wxlf.hammersystemstesttask.viewmodels.State
import kotlin.math.abs

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private val viewModel: MenuFragmentViewModel by viewModels()

    @SuppressLint("UseCompatLoadingForDrawables", "NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        val bannerList = view.findViewById<RecyclerView>(R.id.banner_list)
        bannerList.addItemDecoration(BannerDivider((16 * view.context.resources.displayMetrics.density).toInt()))
        bannerList.adapter = BannerListAdapter(List(2) { 0 })

        val categoryList = view.findViewById<RecyclerView>(R.id.category_list)
        categoryList.addItemDecoration(CategoryDivider((16 * view.context.resources.displayMetrics.density).toInt()))
        val categoryListAdapter = CategoryListAdapter(view.context, listOf("Пицца", "Комбо", "Десерты", "Напитки"))
        categoryListAdapter.onCategoryClick = { position ->
            categoryListAdapter.setChoosed(position)
            categoryListAdapter.notifyDataSetChanged()
        }
        categoryList.adapter = categoryListAdapter

        val productsAdapter = ProductListAdapter()
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.DefaultState -> {}
                is State.ErrorState<*> -> Log.e("TAG", state.message.toString())
                is State.LoadedState -> {
                    productsAdapter.updateData(state.productsList)
                    productsAdapter.notifyDataSetChanged()
                }
            }
        }
        val productsList = view.findViewById<RecyclerView>(R.id.products_list)
        productsList.adapter = productsAdapter

        val appBar = view.findViewById<AppBarLayout>(R.id.app_bar)
        appBar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) == appBarLayout.totalScrollRange) {
                categoryList.elevation = 10 * view.context.resources.displayMetrics.density
                categoryList.setPadding(0, (16 * view.context.resources.displayMetrics.density).toInt(), 0, categoryList.paddingBottom)
            } else {
                categoryList.elevation = 0f
            }
        }
        viewModel.getProducts()
        return view
    }
}