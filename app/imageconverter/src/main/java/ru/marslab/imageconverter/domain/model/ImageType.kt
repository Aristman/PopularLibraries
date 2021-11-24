package ru.marslab.imageconverter.domain.model

sealed class ImageType(val ext: String) {
    object Jpg : ImageType(".jpg")
    object Png : ImageType(".png")
}
