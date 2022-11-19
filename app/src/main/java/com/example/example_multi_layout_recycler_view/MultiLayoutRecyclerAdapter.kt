package com.example.example_multi_layout_recycler_view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.example_multi_layout_recycler_view.databinding.Layout1Binding
import com.example.example_multi_layout_recycler_view.databinding.Layout2Binding

data class Layout1Data(
    val country: String,
    val city: String
)

data class Layout2Data(
    val name: String,
    val number: Int
)

class MultiLayoutRecyclerAdapter :
    RecyclerView.Adapter<MultiLayoutRecyclerAdapter.ViewHolder>() {

    private val list = listOf(
        Layout1Data("Japan", "Tokyo"),
        Layout2Data("Alice", 1),
        Layout2Data("Bob", 2),
        Layout2Data("Cathy", 3),
        Layout2Data("Daniel", 4),
        Layout2Data("Elza", 5),
        Layout2Data("Fiona", 6),
        Layout2Data("Gina", 7),
        Layout2Data("Helen", 8),
    )

    private enum class ViewType(val layoutId: Int) {
        LAYOUT_1(R.layout.layout_1),
        LAYOUT_2(R.layout.layout_2),
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            ViewType.values()[viewType].layoutId,
            viewGroup,
            false
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        when(val data = list[position]) {
            is Layout1Data -> viewHolder.bind(data)
            is Layout2Data -> viewHolder.bind(data)
            else -> return
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(list[position]) {
            is Layout1Data -> ViewType.LAYOUT_1.ordinal
            is Layout2Data -> ViewType.LAYOUT_2.ordinal
            else -> ViewType.LAYOUT_2.ordinal
        }
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Layout1Data) {
            (binding as Layout1Binding).run {
                country.text = data.country
                city.text = data.city
            }
        }

        fun bind(data: Layout2Data) {
            (binding as Layout2Binding).run {
                name.text = data.name
                name.setTextColor(Color.BLUE)
                number.text = data.number.toString()
            }
        }
    }
}