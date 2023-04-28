package com.api.protecaoanimal.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TemperamentosEnum {

    AGRESSIVO("Agressivo", "Rosna ou mostrar os dentes, podendo morder ou arranhar"),
    AFETUOSO("Afetuoso", "É lealdade aos seus donos. Adora carinho e fica feliz em passar o tempo com os donos."),
    BRINCALHAO("Brincalhão", "Adora passar horas brincando com seus donos e com brinquedos."),
    CURIOSO("Curioso", "É curioso e adora explorar o mundo ao seu redor. Precisa de uma boa supervisão para garantir que não se machuque."),
    DOCIO("Dócio", "É tranquilo, tolerantes e fácil de lidar"),
    ENERGETICO("Enérgico", "Tem muita enérgia e está sempre feliz e saudáveis, adora correr e brincar."),
    INDEPENDENTE("Independente", "É mais independente e prefere fazer as coisas do seu jeito. Não precisar de tanta atenção e carinho quanto outros animais de estimação."),
    MEDROSO("Medroso", "É mais tímido ou têm medo de certas coisas, como barulhos altos, outros animais ou pessoas desconhecidas. É importante ter paciência e compreensão com esses animais e ajudá-los a superar seus medos, fornecendo um ambiente seguro e tranquilizador. "),
    PROTETOR("Protetor", "É Protetor de seus donos e da casa. Ele pode latir ou rosnar para estranhos ou outros animais para manter sua família segura."),
    RELAXADO("Relaxado", "É mais relaxado e prefere passar seu tempo cochilando ou descansando ao lado de seus donos."),
    SOCIAVEL("Sociável", "Se dar bem com outros animais");

    private String nome;
    private String description;

}
