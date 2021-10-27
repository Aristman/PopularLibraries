package ru.marslab.imageconverter.data.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.marslab.imageconverter.domain.logD
import ru.marslab.imageconverter.domain.model.ImageType
import ru.marslab.imageconverter.domain.repository.MainRepository
import java.io.File
import java.util.concurrent.TimeUnit

private const val LOAD_DELAY = 1000L

class MainRepositoryImpl : MainRepository {
    override fun loadImage(image: String, type: ImageType): Single<Bitmap> =
        Single
            .fromCallable {
                val file = File(image)
                BitmapFactory.decodeFile(file.absolutePath)
            }
            .doOnSuccess {
                logD("load onSuccess: $it")
            }
            .doOnError {
                logD("load onError: ${it.message}")
            }
            .delay(LOAD_DELAY, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
}