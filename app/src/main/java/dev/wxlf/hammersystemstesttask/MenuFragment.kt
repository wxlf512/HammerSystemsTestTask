package dev.wxlf.hammersystemstesttask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        val bannerList = view.findViewById<RecyclerView>(R.id.banner_list)
        bannerList.addItemDecoration(BannerDivider((16 * view.context.resources.displayMetrics.density).toInt()))
        bannerList.adapter = BannerListAdapter(List<Int>(2) { 0 })
        val categoryList = view.findViewById<RecyclerView>(R.id.category_list)
        categoryList.addItemDecoration(CategoryDivider((16 * view.context.resources.displayMetrics.density).toInt()))
        categoryList.adapter = CategoryListAdapter(listOf("Пицца", "Комбо", "Десерты", "Напитки"))
        val productsList = view.findViewById<RecyclerView>(R.id.products_list)
        productsList.adapter = ProductListAdapter(List<Int>(10) { 0 })
        val categoryListContainer = view.findViewById<LinearLayout>(R.id.category_list_container)
        val appBar = view.findViewById<AppBarLayout>(R.id.app_bar)
        appBar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) == appBarLayout.totalScrollRange) {
                categoryListContainer.elevation = 10 * view.context.resources.displayMetrics.density
            } else {
                categoryListContainer.elevation = 0f
            }
        }
        return view
    }
}