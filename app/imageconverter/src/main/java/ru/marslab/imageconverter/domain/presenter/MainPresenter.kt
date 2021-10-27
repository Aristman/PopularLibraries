package ru.marslab.imageconverter.domain.presenter

import moxy.MvpPresenter
import ru.marslab.imageconverter.domain.model.ImageType
import ru.marslab.imageconverter.domain.repository.MainRepository
import ru.marslab.imageconverter.ui.view.MainView

class MainPresenter(
    private val mainRepository: MainRepository
) : MvpPresenter<MainView>() {

    fun convertJpgToPng(image: String, type: ImageType) {
        mainRepository.loadImage(image, type)
    }

}