package ru.marslab.imageconverter.domain.model

sealed class ImageType {
    object Jpg : ImageType()
    object Png : ImageType()
}
