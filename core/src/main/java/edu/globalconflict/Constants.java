package edu.globalconflict;

import com.badlogic.gdx.graphics.Color;

import java.util.Arrays;
import java.util.List;

/**
 * @author mateusz
 * @since 10.08.14
 */
public interface Constants {
    /* Screen constants */
    float SCREEN_WIDTH = 800.f;
    float SCREEN_HEIGHT = 480.f;

    /* World */
    float WORLD_WIDTH = 3000.f;
    float WORLD_HEIGHT = 1487.f;

    /* Game entity name */
    String GAME_ENTITY = "state";

    /* Debug mode */
    boolean DEBUG = false;

    /* Player data */
    List<Color> PLAYER_COLORS = Arrays.asList(
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.ORANGE,
            Color.MAGENTA,
            Color.WHITE
    );

    /* Territory names */
    String ALASKA = "Alaska";
    String ALBERTA = "Alberta";
    String CENTRAL_AMERICA = "Central America";
    String EASTERN_US = "Eastern United States";
    String GREENLAND = "Greenland";
    String NORTHWEST_TERRITORY = "Northwest Territory";
    String ONTARIO = "Ontario";
    String QUEBEC = "Quebec";
    String WESTERN_US = "Western United States";
    String ARGENTINA = "Argentina";
    String BRAZIL = "Brazil";
    String PERU = "Peru";
    String VENEZUELA = "Venezuela";
    String GREAT_BRITAIN = "Great Britain";
    String ICELAND = "Iceland";
    String NORTHERN_EUROPE = "Northern Europe";
    String SCANDINAVIA = "Scandinavia";
    String SOUTHERN_EUROPE = "Southern Europe";
    String UKRAINE = "Ukraine";
    String WESTERN_EUROPE = "Western Europe";
    String CONGO = "Congo";
    String EAST_AFRICA = "East Africa";
    String EGYPT = "Egypt";
    String MADAGASCAR = "Madagascar";
    String NORTH_AFRICA = "North Africa";
    String SOUTH_AFRICA = "South Africa";
    String AFGHANISTAN = "Afghanistan";
    String CHINA = "China";
    String INDIA = "India";
    String IRKUTSK = "Irkutsk";
    String JAPAN = "Japan";
    String KAMCHATKA = "Kamchatka";
    String MIDDLE_EAST = "Middle East";
    String MONGOLIA = "Mongolia";
    String SIAM = "Siam";
    String SIBERIA = "Siberia";
    String URAL = "Ural";
    String YAKUTSK = "Yakutsk";
    String EASTERN_AUSTRALIA = "Eastern Australia";
    String INDONESIA = "Indonesia";
    String NEW_GUINEA = "New Guinea";
    String WESTERN_AUSTRALIA = "Western Australia";

    /* Error messages */
    String AT_LEAST_ONE_TERRITORY_SELECTED = "You have to select a territory";
    String TRANSFER_FAILED = "You cannot perform transfer on this territory";

    String AT_LEAST_TWO_TERRITORIES_SELECTED = "You have to select at least two territories";
    String TERRITORIES_ARE_NOT_NEIGHBORS = "You must select two neighboring territories";
    String SAME_PLAYER_TERRITORIES = "You must choose other player's territory for attack";
    String ATTACK_FROM_NOT_CURRENT_PLAYER = "You must attack from your territory";
    String FROZEN_TERRITORY = "You cannot attack with this territory";

    /* Save file name */
    String SAVE_FILE = ".savefile.json";
}
