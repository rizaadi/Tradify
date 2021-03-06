package com.example.tradify.ui.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tradify.model.Categories
import com.example.tradify.model.Product
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeViewModel : ViewModel() {
    private val _productuser = MutableLiveData<List<Product>>()
    val productuser: LiveData<List<Product>> = _productuser

    private val _categoryuser = MutableLiveData<List<Categories>>()
    val categoryuser: LiveData<List<Categories>> = _categoryuser


    val db = Firebase.firestore

    fun getProductList() {
        db.collection("products")
            .get()
            .addOnSuccessListener { documents ->
                val productsList: ArrayList<Product> = ArrayList()
                for (i in documents.documents) {
                    val product = i.toObject(Product::class.java)
                    product!!.product_id = i.id
                    productsList.add(product)
                }
                _productuser.value = productsList
            }
            .addOnFailureListener { e ->
                Log.e("Get Product List", "Error while getting product list.", e)
            }
    }

    fun getCategoryList() {
        db.collection("categories")
            .get()
            .addOnSuccessListener { documents ->
                val categoryList: ArrayList<Categories> = ArrayList()
                for (i in documents.documents) {
                    val product = i.toObject(Categories::class.java)
                    product!!.id = i.id
                    categoryList.add(product)
                }
                _categoryuser.value = categoryList
            }
            .addOnFailureListener { e ->
                Log.e("Get Product List", "Error while getting product list.", e)
            }
    }

}