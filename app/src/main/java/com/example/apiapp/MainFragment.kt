package com.example.apiapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.apiapp.databinding.FragmentMainBinding
import retrofit2.HttpException
import java.io.IOException

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var productAdapter: ProductAdapter

    private val _navigateToSelectedProperty = MutableLiveData<Product>()
    val navigateToSelectedProperty: LiveData<Product>
        get() = _navigateToSelectedProperty
    val TAG = "MainActivity"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getProducts()
            } catch (e: IOException) {
                Log.e(TAG, "IOException")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null) {
                productAdapter.todos = response.body()!!.products
            } else {
                Log.e(TAG, "Response not successful")
            }

            binding.progressBar.isVisible = false
        }

        return binding.root
    }

    private fun setupRecyclerView() = binding.rvProducts.apply{
        productAdapter = ProductAdapter()
        adapter = productAdapter
        layoutManager = GridLayoutManager( activity, 3)

        productAdapter.onItemClick = { product ->

            val bundle = Bundle()
            bundle.putSerializable("product", product)
            view?.findNavController()?.navigate(R.id.action_mainFragment_to_detailsFragment, bundle)


        }

    }

//    fun displayPropertyDetails(product: Product) {
//        _navigateToSelectedProperty.value = product
//    }

//    fun displayPropertyDetailsComplete() {
//        _navigateToSelectedProperty.value = null
//    }


}