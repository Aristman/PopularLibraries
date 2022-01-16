package ru.marslab.imageconverter

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.marslab.imageconverter.domain.model.ImageType
import ru.marslab.imageconverter.domain.presenter.MainPresenter
import ru.marslab.imageconverter.domain.repository.MainRepository

class MainPresenterTest {
    private lateinit var mainPresenter: MainPresenter

    @Mock
    private lateinit var mainRepository: MainRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainPresenter = MainPresenter(mainRepository)
    }

    @Test
    fun convertJpgToPng_Test() {
        val image = "test_image"
        mainPresenter.convertJpgToPng(image)
        Mockito.verify(mainRepository, Mockito.times(1)).convertToPng(
            image,
            ImageType.Png,
            bitmapSource = null
        )
    }
}
