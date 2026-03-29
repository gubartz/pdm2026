package br.edu.ifsp.hto.htoipdm.applazycolumn.data.mock

import androidx.compose.runtime.mutableStateListOf

/*
TODO(1) Neste arquivo serão criadas 3 funções que retornam listas de
 nomes.
 */

/*
TODO(2) A função getNomes retorna uma lista de Strings de nomes. Está lista não pode ser alterada
 como, por exemplo, adicionar, remover ou alterar elementos.
 */
fun getNomes(): List<String> {
    val nomes = listOf(
        "Ana", "Bruno", "Carlos", "Daniela", "Eduardo",
        "Fernanda", "Gabriel", "Helena", "Igor", "Juliana",
        "Kleber", "Larissa", "Marcos", "Natália", "Otávio",
        "Patrícia", "Rafael", "Sabrina", "Thiago", "Úrsula",
        "Vinícius", "William", "Xavier", "Yasmin", "Zeca",
        "Adriana", "Breno", "Camila", "Diego", "Elaine",
        "Felipe", "Giovana", "Hugo", "Isabela", "João",
        "Karina", "Leonardo", "Marta", "Nicolas", "Olívia",
        "Paulo", "Renata", "Samuel", "Tatiane", "Ubirajara",
        "Valéria", "Wesley", "Ximena", "Yuri", "Zilda",
        "André", "Bianca", "César", "Débora", "Erick",
        "Fabiana", "Gustavo", "Heloísa", "Ivan", "Jéssica",
        "Kaio", "Lívia", "Mateus", "Neide", "Orlando",
        "Priscila", "Rodrigo", "Simone", "Tadeu", "Ulisses",
        "Vanessa", "Washington", "Xisto", "Yara", "Zuleica",
        "Aline", "Bernardo", "Cláudia", "Davi", "Elisa",
        "Fábio", "Geovana", "Heitor", "Iara", "Jonas",
        "Kelly", "Luana", "Murilo", "Nina", "Osvaldo",
        "Pâmela", "Rogério", "Sérgio", "Talita", "Uriel"
    )

    return nomes
}

/*
TODO(3) A função getNomes2 retorna uma lista mutável de Strings de nomes. Está lista pode ser
 alterada podendo adicionar, remover ou alterar elementos. Entretanto, esta lista não é observável,
 logo ao se adicionar elementos a UI não é atualizada automaticamente.
 */
fun getNomes2(): MutableList<String> {
    val nomes = mutableListOf(
        "Ana", "Bruno", "Carlos", "Daniela", "Eduardo",
        "Fernanda", "Gabriel", "Helena", "Igor", "Juliana",
        "Kleber", "Larissa", "Marcos", "Natália", "Otávio",
        "Patrícia", "Rafael", "Sabrina", "Thiago", "Úrsula",
        "Vinícius", "William", "Xavier", "Yasmin", "Zeca",
        "Adriana", "Breno", "Camila", "Diego", "Elaine",
        "Felipe", "Giovana", "Hugo", "Isabela", "João",
        "Karina", "Leonardo", "Marta", "Nicolas", "Olívia",
        "Paulo", "Renata", "Samuel", "Tatiane", "Ubirajara",
        "Valéria", "Wesley", "Ximena", "Yuri", "Zilda",
        "André", "Bianca", "César", "Débora", "Erick",
        "Fabiana", "Gustavo", "Heloísa", "Ivan", "Jéssica",
        "Kaio", "Lívia", "Mateus", "Neide", "Orlando",
        "Priscila", "Rodrigo", "Simone", "Tadeu", "Ulisses",
        "Vanessa", "Washington", "Xisto", "Yara", "Zuleica",
        "Aline", "Bernardo", "Cláudia", "Davi", "Elisa",
        "Fábio", "Geovana", "Heitor", "Iara", "Jonas",
        "Kelly", "Luana", "Murilo", "Nina", "Osvaldo",
        "Pâmela", "Rogério", "Sérgio", "Talita", "Uriel"
    )

    return nomes
}

/*
TODO(4) A função getNomes3 retorna uma lista mutável e observável de Strings de nomes. Está lista
 pode ser alterada podendo adicionar, remover ou alterar elementos. Além disso, esta lista é
 observável, logo ao se adicionar elementos a UI é atualizada automaticamente.
 */
fun getNomes3(): MutableList<String> {
    val nomes = mutableStateListOf(
        "Ana", "Bruno", "Carlos", "Daniela", "Eduardo",
        "Fernanda", "Gabriel", "Helena", "Igor", "Juliana",
        "Kleber", "Larissa", "Marcos", "Natália", "Otávio",
        "Patrícia", "Rafael", "Sabrina", "Thiago", "Úrsula",
        "Vinícius", "William", "Xavier", "Yasmin", "Zeca",
        "Adriana", "Breno", "Camila", "Diego", "Elaine",
        "Felipe", "Giovana", "Hugo", "Isabela", "João",
        "Karina", "Leonardo", "Marta", "Nicolas", "Olívia",
        "Paulo", "Renata", "Samuel", "Tatiane", "Ubirajara",
        "Valéria", "Wesley", "Ximena", "Yuri", "Zilda",
        "André", "Bianca", "César", "Débora", "Erick",
        "Fabiana", "Gustavo", "Heloísa", "Ivan", "Jéssica",
        "Kaio", "Lívia", "Mateus", "Neide", "Orlando",
        "Priscila", "Rodrigo", "Simone", "Tadeu", "Ulisses",
        "Vanessa", "Washington", "Xisto", "Yara", "Zuleica",
        "Aline", "Bernardo", "Cláudia", "Davi", "Elisa",
        "Fábio", "Geovana", "Heitor", "Iara", "Jonas",
        "Kelly", "Luana", "Murilo", "Nina", "Osvaldo",
        "Pâmela", "Rogério", "Sérgio", "Talita", "Uriel"
    )

    return nomes
}