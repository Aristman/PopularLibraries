package ru.marslab.imageconverter.domain.repository

import android.graphics.Bitmap
import io.reactivex.Observable
import ru.marslab.imageconverter.domain.model.ImageStatus
import ru.marslab.imageconverter.domain.model.ImageType

interface MainRepository {
    fun loadImage(image: String, type: ImageType): Observable<ImageStatus>
    fun convertToPng(
        image: String,
        imageType: ImageType,
        bitmapSource: Bitmap?
    ): Observable<ImageStatus>
}