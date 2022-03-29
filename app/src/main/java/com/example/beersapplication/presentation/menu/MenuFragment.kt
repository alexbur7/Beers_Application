package com.example.beersapplication.presentation.menu

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.beersapplication.R
import com.example.beersapplication.databinding.FragmentMenuBinding
import com.example.beersapplication.presentation.main.appComponent
import com.example.beersapplication.presentation.menu.adapter.BannerAdapter
import com.example.beersapplication.presentation.menu.adapter.BeersAdapter
import com.example.beersapplication.presentation.menu.adapter.TypesAdapter
import com.example.beersapplication.presentation.util.factory.DecorationFactory
import com.example.beersapplication.presentation.util.factory.ViewModelFactory
import javax.inject.Inject

class MenuFragment : Fragment(R.layout.fragment_menu) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var decorationFactory: DecorationFactory

    private val viewBinding by viewBinding(FragmentMenuBinding::bind)
    private val viewModel: MenuViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val beersAdapter = BeersAdapter()
        with(viewBinding) {
            bannersList.run {
                layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.HORIZONTAL, false
                )
                adapter = BannerAdapter()
                addItemDecoration(decorationFactory.create(leftMargin = 32, rightMargin = 32))
            }

            typesList.run {
                layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.HORIZONTAL, false
                )
                addItemDecoration(
                    decorationFactory.create(
                        leftMargin = 16,
                        rightMargin = 16,
                        bottomMargin = 16
                    )
                )
                adapter = TypesAdapter(::typeClick)
            }

            beersList.run {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = beersAdapter
            }

            viewModel.beersData.observe(viewLifecycleOwner) { beers ->
                beersAdapter.setData(beers)
            }

            viewModel.errorMessageIdData.observe(viewLifecycleOwner) { messageId ->
                Toast.makeText(context, messageId, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun typeClick(type: Int) {
        when (type) {
            0 -> viewModel.getAllBeers()
            1 -> viewModel.getStrongBeers()
            else -> {
                Toast.makeText(requireContext(), R.string.un_support_category, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
