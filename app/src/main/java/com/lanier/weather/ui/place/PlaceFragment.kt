package com.lanier.weather.ui.place

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lanier.weather.R
import com.lanier.weather.logic.model.PlaceX

class PlaceFragment : Fragment() {


    private val viewModel: PlaceViewModel by viewModels()


    private lateinit var adapter: PlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    @SuppressLint("NotifyDataSetChanged", "CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycleView) // 确保 id 正确
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        adapter = PlaceAdapter(viewModel.placeList) // 确保 `this` 是 Fragment
        recyclerView.adapter = adapter

        val edittext = view.findViewById<EditText>(R.id.searchPlaceEdit)
        view.findViewById<Button>(R.id.btnSearch).setOnClickListener {
            val place = edittext.text.toString()
            viewModel.searchPlaces(place)
        }

        viewModel.searchLiveData.observe(viewLifecycleOwner) {
            if (it == null) {
                recyclerView.visibility = View.GONE
                Toast.makeText(activity, "未能查询到任何地点", Toast.LENGTH_SHORT).show()
            } else {
                recyclerView.visibility = View.VISIBLE
                view.findViewById<ImageView>(R.id.bgImageView)?.visibility = View.VISIBLE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }
    }
}
