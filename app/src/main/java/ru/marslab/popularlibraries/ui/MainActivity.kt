package ru.marslab.popularlibraries.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import ru.marslab.popularlibraries.App
import ru.marslab.popularlibraries.databinding.ActivityMainBinding
import ru.marslab.popularlibraries.model.CounterButton
import ru.marslab.popularlibraries.view.MainView

private const val COUNTERS_ARRAY_TAG = "COUNTERS_ARRAY_TAG"

class MainActivity : AppCompatActivity(), MainView {

    private val presenter by lazy { App().getPresenter() }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(
            LayoutInflater.from(
                this
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this)
        setContentView(binding.root)
        savedInstanceState?.getIntArray(COUNTERS_ARRAY_TAG)?.let { presenter.setCounters(it) }
        initListeners()
    }

    private fun initListeners() {
        binding.root.forEach { view ->
            if (view is Button) {
                view.setOnClickListener {
                    presenter.buttonClick(
                        when (view.id) {
                            binding.btnCounter1.id -> CounterButton.ButtonOne
                            binding.btnCounter2.id -> CounterButton.ButtonTwo
                            binding.btnCounter3.id -> CounterButton.ButtonThree
                            else -> null
                        }
                    )
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.apply {
            putIntArray(COUNTERS_ARRAY_TAG, presenter.getCounters())
        }
        super.onSaveInstanceState(outState)
    }

    override fun setButtonText(button: CounterButton?, text: String) {
        when (button) {
            CounterButton.ButtonOne -> {
                binding.btnCounter1.text = text
            }
            CounterButton.ButtonTwo -> {
                binding.btnCounter2.text = text
            }
            CounterButton.ButtonThree -> {
                binding.btnCounter3.text = text
            }
        }
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }
}