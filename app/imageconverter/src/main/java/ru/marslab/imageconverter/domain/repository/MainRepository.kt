package ru.marslab.imageconverter.domain.repository

import android.graphics.Bitmap
import io.reactivex.Single
import ru.marslab.imageconverter.domain.model.ImageType

interface MainRepository {
    fun loadImage(image: String, type: ImageType): Single<Bitmap>
}