package com.medjay.employment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.medjay.employment.adapters.CategoryQuestionAdapter;
import com.medjay.employment.adapters.CategoryUnderAdapter;

import java.util.ArrayList;

public class CategoryQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_question);

        String category=getIntent().getExtras().getString("category");

        ArrayList<String> categories=new ArrayList<>();

        assert category != null;
        switch (category){
            case "Murs & sols":
            {categories.add("Peiture d'intérieur");
                categories.add("Pose ou réparation de parquet");
                categories.add("Pose de carrelage");
                categories.add("Rénovation de maison ou d'apparetement");
                categories.add("Enduit de mur");
                categories.add("Pose de papier peint");
                categories.add("Démolition");
                categories.add("Pose de lino");
                categories.add("Bouchage de trou ou fissure");
                categories.add("Décollage de papier peint");
                categories.add("Pose de béton ciré");
                categories.add("Pose ou remplacement de moquette");
                categories.add("Assainissement de cave");
                categories.add("Pose de jonc de mer");
                categories.add("Autres");}
                break;
            case "Plombrie & chauffage":
            {categories.add("Pose de joints");
                categories.add("Réparation de fuite d'eau");
                categories.add("Installation ou remplacement de chauffe-eau");
                categories.add("Rénovation de maison ou d'apparetement");
                categories.add("Réparation de chaudière");
                categories.add("Pose de baignoire");
                categories.add("Réparation de robinet");
                categories.add("Installation ou remplacement de chaudière débouchage de toilettes");
                categories.add("Débouchage d'évier");
                categories.add("Pose ou remplacement d'évier");
                categories.add("Réparation de chauffe-eau");
                categories.add("Pose ou remplacement d'un mitigeur");
                categories.add("Purge de radiateur");
                categories.add("Débouchage de baignoire");
                categories.add("Installation de sèche-serviette");
                categories.add("Débouchage de douche");
                categories.add("Réparation de climatisation");
                categories.add("Ramonage de cheminée");
                categories.add("Pose de climatiseur fixe");
                categories.add("Ramonage de poêle");
                categories.add("Pose de climatiseur mobile");
                categories.add("Autres");}
            break;
            case "Mobiliers & décorations":
            {categories.add("Montage de meuble");
                categories.add("Accrochage de tableaux, cadres et miroir");
                categories.add("Pose de tringle à rideaux");
                categories.add("Fixation d'étagère");
                categories.add("Installation de télévision murale");
                categories.add("Démontage de meubles");
                categories.add("Réparation de meubles");
                categories.add("Restauration de meubles");
                categories.add("Architecture d'intérieur");
                categories.add("Création de meubles, placards et dressing");
                categories.add("Autres");}
            break;
            case "Cuisine & électroménagers":
            {categories.add("Pose de cuisine");
                categories.add("Réparation de lave-linge");
                categories.add("Réparation de lave-vaisselle");
                categories.add("Pose de plan de travail");
                categories.add("Réparation de réfrigérateur");
                categories.add("Réparation de four");
                categories.add("Réparation sèche-linge");
                categories.add("Installation de lave-linge");
                categories.add("Installation de lave-vaisselle");
                categories.add("Installation de hotte");
                categories.add("Installation four");
                categories.add("Réparation de sèche-linge");
                categories.add("IInstallation de lave-linge");
                categories.add("Installation de lave-vaisselle");
                categories.add("Installation de hotte");
                categories.add("Installation de four");
                categories.add("Pose de plaque de cuisson");
                categories.add("Pose de crédence");
                categories.add("Réparation de congélateur");
                categories.add("Réparation de micro-ondes");
                categories.add("Autres");}
            break;
            case "Eléctricitié & domotique":
            {categories.add("Installation de luminaire");
            categories.add("Pose ou remplacement de prise électrique");
            categories.add("Recherche de panne électrique");
            categories.add("Pose de radiateur électrique");
            categories.add("Réparation de lampe");
            categories.add("Changement d'ampoule");
            categories.add("Installation d'interphone");
            categories.add("Réparation de radiateur électrique");
            categories.add("Pose et remplacement d'interrupteur");
            categories.add("Pose de thermostat d'ambiance");
            categories.add("Remplacement de fusible");
            categories.add("Installation de sonnette");
            categories.add("Installation de minuterie");
            categories.add("Installation de détecteur de fumée");
            categories.add("Installation de système audio");
            categories.add("Installation d'alarme sans fil");
            categories.add("Autres");}
            break;
            case "Portes & fenêtres":
            {categories.add("Peinture de volets en bois");
                categories.add("Réparation de volet roulant");
                categories.add("Réparation de serrure");
                categories.add("Ouverture de porte");
                categories.add("Changement de vitres");
                categories.add("Installation ou changement de serrure");
                categories.add("Pose ou remplacement de volet roulant");
                categories.add("Réparation de fenêtres");
                categories.add("Rabotage de portes");
                categories.add("Installation ou remplacement de poignées de porte");
                categories.add("Installation de portes");
                categories.add("Installation de fenêtres");
                categories.add("Pose ou remplacement de store interieur");
                categories.add("Rabotage de fenêtres");
                categories.add("Blindage de portes");
                categories.add("Autres");
            }
            break;

            case "Déménagement":
            {categories.add("Déménagement avec camion");
                categories.add("Aide au déménagements");
                categories.add("Transport d'objets lourds");
                categories.add("Enlèvement d'encombrants");
                categories.add("Evacuation de gravats");
                categories.add("Stockage de meubles");
                categories.add("Autres");
            }
            break;

            case "Transport":
            break;

            case "Entretien de jardins & terrasses":
            {categories.add("Entretien de jardin");
                categories.add("Taille de haie");
                categories.add("Enlèvement de déchets");
                categories.add("Verts débroussaillage");
                categories.add("Tonte de pelouse");
                categories.add("Coupe d'arbre");
                categories.add("Ramassage de feuilles");
                categories.add("Jardinage à l'heure");
                categories.add("Abattage d'arbre");
                categories.add("Platation de fleurs");
                categories.add("Nettoyage de terrasses");
                categories.add("Arrachage d'arbres");
                categories.add("Entretien de piscines");
                categories.add("Engazonnement de jardin");
                categories.add("Scarification de pelouse");
                categories.add("Désherbage");
                categories.add("Arrosage de plantes pendant les vacances");
                categories.add("Paysagiste");
                categories.add("Bechage");
                categories.add("Platation d'arbres");
                categories.add("Labourage");
                categories.add("Autres");
            }
            break;

            case "Mobiliers & abris de jardins":
            {categories.add("Montage d'un abri de jardin");
                categories.add("Montage de balançoire");
                categories.add("Montage de salon de jardin");
                categories.add("Montage de pergola");
                categories.add("Autres");
            }
            break;

            case "Toiture & façades":
            break;

            case "Clotures & portails":
            break;

            case "Terrasses & balcons":
            break;

            case "Piscines & jacuzzis":
            break;
        }



        ListView listView =findViewById(R.id.lv);

        CategoryQuestionAdapter adapter =new CategoryQuestionAdapter(CategoryQuestionActivity.this, R.layout.category_under,categories);

        listView.setAdapter(adapter);

    }
}
