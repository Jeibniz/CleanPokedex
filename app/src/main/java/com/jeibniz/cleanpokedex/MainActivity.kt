package com.jeibniz.cleanpokedex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var fragmentFactory: FragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDagger()
        setFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        /*
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PokemonListFragment.newInstance())
                .commitNow()
        }

         */
    }

    protected fun injectDagger() {
        (application as BaseApplication).appComponent.inject(this)
    }

    private fun setFragmentFactory() {
        supportFragmentManager.fragmentFactory = fragmentFactory!!
    }
}