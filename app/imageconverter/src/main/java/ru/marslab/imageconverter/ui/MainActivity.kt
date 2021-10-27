package ru.marslab.imageconverter.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.marslab.imageconverter.App
import ru.marslab.imageconverter.databinding.ActivityMainBinding
import ru.marslab.imageconverter.domain.presenter.MainPresenter
import ru.marslab.imageconverter.ui.view.MainView

class MainActivity : MvpAppCompatActivity(), MainView {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainPresenter by moxyPresenter { MainPresenter(App.instance.mainRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initListeners()
    }

    @SuppressLint("NewApi")
    private fun initListeners() {
        binding.run {
            btnConvertJpgPng.setOnClickListener {
                mainPresenter.convertJpgToPng(dataDir.absolutePath + "/Curiosity_middle")
            }
            btnCancel.setOnClickListener {
                mainPresenter.cancelAllJobs()
            }
        }
    }

    override fun init() {
        hideInfoBlock()
    }

    override fun showInfoBlock() {
        binding.loadingGroup.visibility = View.VISIBLE
    }

    override fun hideInfoBlock() {
        binding.loadingGroup.visibility = View.GONE
    }

    override fun setLoadProgress(progress: Int) {
        binding.loadingProgress.progress = progress
    }

    override fun setStageText(text: String) {
        binding.convertStage.text = text
    }

    override fun showErrorToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun isEnableConvertButton(isEnabled: Boolean) {
        binding.btnConvertJpgPng.isEnabled = isEnabled
    }
}