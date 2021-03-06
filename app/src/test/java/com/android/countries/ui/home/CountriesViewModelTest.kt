package com.android.countries.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.android.countries.data.repository.CountriesRepository
import com.android.countries.db.model.CountriesData
import com.android.countries.db.model.Languages
import com.android.countries.db.model.Name
import com.android.countries.db.model.Translations
import com.android.countries.util.AppResult
import com.android.countries.utils.MainCoroutineRule
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.verify
import demo.movie.db.kotlin.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class CountriesViewModelTest {
    private val repository: CountriesRepository = Mockito.mock(CountriesRepository::class.java)

    private lateinit var viewModel: CountriesViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var countriesListObserver: Observer<List<CountriesData>>

    @Mock
    lateinit var showErrorObserver: Observer<String>
    
    @Before
    fun setUp() {
        // create view model
        viewModel = CountriesViewModel(repository)

        viewModel.countriesList.observeForever(countriesListObserver)
        viewModel.showError.observeForever(showErrorObserver)
    }
    
    @After
    fun tearDown() {
        reset(countriesListObserver)
        reset(showErrorObserver)
    }

    @Test
    fun `should fetch data from api successfully`() {
        runBlocking {
            launch(Dispatchers.Main) {
                // given
                Mockito.`when`(repository.getAllCountries()).thenReturn(AppResult.Success(mockData))

                // when
                viewModel.getAllCountries()

                // then
                val observeMovieListCapture = argumentCaptor<List<CountriesData>>()
                viewModel.countriesList.getOrAwaitValue()
                verify(countriesListObserver).onChanged(observeMovieListCapture.capture())

                Assert.assertEquals(
                    mockData,
                    observeMovieListCapture.firstValue
                )

                val observeErrorCapture = argumentCaptor<String>()
                viewModel.showError.getOrAwaitValue()
                verify(showErrorObserver).onChanged(observeErrorCapture.capture())

                Assert.assertNull(observeErrorCapture.firstValue)
            }
        }
    }

    @Test
    fun `should show error when fetching movie from api`() {
        runBlocking {
            launch(Dispatchers.Main) {
                // given
                Mockito.`when`(repository.getAllCountries()).thenReturn(AppResult.Error(Exception("Error!")))

                // when
                viewModel.getAllCountries()

                // then
                val observeErrorCapture = argumentCaptor<String>()
                viewModel.showError.getOrAwaitValue()
                verify(showErrorObserver).onChanged(observeErrorCapture.capture())

                Assert.assertNotNull(observeErrorCapture.firstValue)
            }
        }
    }

    private val mockData = listOf(CountriesData(
        1,
        name = Name("USA", "USA", null),
        tld =  mockTldData(),
        "US",
        "004",
        "US",
        currency = mockCurrencyData(),
        callingCode = mockCallingCodeData(),
        "Wasington DC",
        altSpellings = mockAltSpellingData(),
        "0",
        "America",
        "America",
        "pus",
        languages = Languages(1, "English", "English", "English","English", "English", "English",
            "English", "English", "English","English", "English", "English","English", "English", "English"),
        translations =  Translations(1, "English","English","English","English","English",
            "English","English","English","English","English"),
        "??landish",
        borders = null,
        652230.00
    ),
        CountriesData(
            2,
            name = Name("Bangladesh", "Bangladesh", null),
            tld =  mockTldData(),
            "BD",
            "004",
            "BD",
            currency = mockCurrencyData(),
            callingCode = mockCallingCodeData(),
            "Dhaka",
            altSpellings = mockAltSpellingData(),
            "0",
            "Asia",
            "Southeast Asia",
            "Bangla",
            languages = Languages(1, "Dari", "Pashto", "Turkmen","Dari", "Pashto", "Turkmen",
                "Dari", "Pashto", "Turkmen","Dari", "Pashto", "Turkmen","Dari", "Pashto", "Turkmen"),
            translations =  Translations(1, "Bangali","Bangali","Bangali","Bangali","Bangali",
                "Bangali","Bangali","Bangali","Bangali","Bangali"),
            "BD",
            borders = null,
            852230.00
        ))

    private fun mockTldData() =  listOf(
        ".af", ".bd"
    )

    private fun mockCurrencyData() =  listOf(
        "\"AFN\"", "\"BDT\""
    )

    private fun mockCallingCodeData() =  listOf(
        "93", "88"
    )

    private fun mockAltSpellingData() =  listOf(
        "AF", "Af????nist??n"
    )
    private fun mockNativeLanguaggesData() =  listOf(
        "Dari", "Pashto", "Turkmen"
    )
}