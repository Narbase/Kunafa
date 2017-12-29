package net.avatarapps.dopa.dashboard.dashboard.view.zones.areas

data class State(
        val id: Int,
        val nameAr: String,
        val nameEn: String,
        val districts: ArrayList<District>
)

data class District(
        val id: Int,
        val nameAr: String,
        val nameEn: String,
        val neighbourhoods: ArrayList<Neighbourhood>
)

data class Neighbourhood(
        val id: Int,
        val nameAr: String,
        val nameEn: String
)



object Areas {
    val states = arrayListOf(
            State(1, "الخرطوم", "Khartoum",
                    arrayListOf(
                            District(1, "بحري", "Bahri",
                                    arrayListOf(
                                            Neighbourhood(1, "شمبات جنوب", "South Shambat"),
                                            Neighbourhood(2, "الدروشاب", "Aldroushab"),
                                            Neighbourhood(3, "الصبابي", "Alsababi"),
                                            Neighbourhood(4, "كافوري", "Kafouri"),
                                            Neighbourhood(5, "الكدرو", "Alkadaro")
                                    )
                            )
                    ))
    )
}