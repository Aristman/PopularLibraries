package ru.marslab.imageconverter.domain.presenter

import android.graphics.Bitmap
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.marslab.imageconverter.domain.model.ImageStatus
import ru.marslab.imageconverter.domain.model.ImageType
import ru.marslab.imageconverter.domain.repository.MainRepository
import ru.marslab.imageconverter.ui.view.MainView

private const val LOAD_JPG = "Загрузка JPG-файла"
private const val LOAD_COMPLETE = "Загрузка завершена!"
private const val LOAD_ERROR = "Ошибка загрузки"
private const val CONVERT_TO_PNG = "Конвертация в PNG"
private const val CONVERT_COMPLETE = "Конвертация завершена!"
private const val CONVERT_ERROR = "Ошибка конвертации"

class MainPresenter(
    private val mainRepository: MainRepository
) : MvpPresenter<MainView>() {

    private val disposable = CompositeDisposable()

    private var bitmapSource: Bitmap? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun convertJpgToPng(image: String) {
        disposable.add(
            mainRepository.loadImage(image, ImageType.Jpg)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                }
                .subscribe(
                    { loadingResult ->
                        loadingImage(loadingResult)
                    },
                    {
                        loadingError()
                    },
                    {
                        convertToPng(image)
                    },
                    {
                        loadingInit()
                    })
        )
    }

    private fun convertToPng(image: String) {
        disposable.add(mainRepository.convertToPng(
            image,
            ImageType.Png,
            bitmapSource
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { convertResult ->
                    convertingImage(convertResult)
                },
                {
                    convertImageError()
                },
                {
                    convertSuccessful()
                },
                {
                    convertInit()
                }
            )
        )
    }

    private fun convertInit() {
        viewState.showInfoBlock()
        viewState.setStageText(CONVERT_TO_PNG)
    }

    private fun convertSuccessful() {
        viewState.isEnableConvertButton(true)
        viewState.setStageText(CONVERT_COMPLETE)
    }

    private fun convertingImage(convertResult: ImageStatus?) {
        if (convertResult is ImageStatus.Loading) {
            viewState.setLoadProgress(convertResult.progress)
        }
    }

    private fun convertImageError() {
        viewState.isEnableConvertButton(true)
        viewState.showErrorToast(CONVERT_ERROR)
    }

    private fun loadingError() {
        viewState.showErrorToast(LOAD_ERROR)
        viewState.hideInfoBlock()
    }

    private fun loadingInit() {
        viewState.run {
            showInfoBlock()
            setStageText(LOAD_JPG)
            viewState.isEnableConvertButton(false)
        }
    }

    private fun loadingImage(loadingResult: ImageStatus?) {
        when (loadingResult) {
            is ImageStatus.Loading -> {
                viewState.setLoadProgress(loadingResult.progress)
            }
            is ImageStatus.Successful -> {
                viewState.setStageText(LOAD_COMPLETE)
                bitmapSource = loadingResult.image
            }
        }
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
}