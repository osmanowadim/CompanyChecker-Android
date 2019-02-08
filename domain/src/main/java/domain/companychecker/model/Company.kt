package domain.companychecker.model

import domain.companychecker.interactor.Params

/**
 * Class that represents a Company in the domain layer.
 */
class Company(
    val name: String?,
    val image: String?,
    val url: String?
) : Params()
