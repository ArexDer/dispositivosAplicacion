package com.uce.aplicacion1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView
import com.uce.aplicacion1.R

class MenuBottomSheetFragment : BottomSheetDialogFragment(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navigationView = view.findViewById<NavigationView>(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_edit -> {
                Toast.makeText(context, "Edit clicked", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_delete -> {
                Toast.makeText(context, "Delete clicked", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_share -> {
                Toast.makeText(context, "Share clicked", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return false
    }
}
