import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { environment} from './../../environments/environment';

@Component({
  selector: 'app-search-recipe',
  templateUrl: './search-recipe.component.html',
  styleUrls: ['./search-recipe.component.css']
})
export class SearchRecipeComponent implements OnInit {
  @ViewChild('recipe') recipes: ElementRef;
  @ViewChild('place') places: ElementRef;
  recipeValue: any;
  placeValue: any;
  venueList = [];
  recipeList = [];

  currentLat: any;
  currentLong: any;
  geolocationPosition: any;
  private venueAppId = environment.venuesApiID;
  private venueAppkey = environment.venuesApiKey;
  private foodAppId = environment.foodApiID;
  private foodAppKey = environment.foodApiKey;
  private recipeUrl = `https://api.edamam.com/search?app_id=${this.foodAppId}&app_key=${this.foodAppKey}`;
  private venueUrl = `https://api.foursquare.com/v2/venues/explore?v=20180323&client_id=${this.venueAppId}&client_secret=${this.venueAppkey}`;
  constructor(private _http: HttpClient) {
  }

  ngOnInit() {

    window.navigator.geolocation.getCurrentPosition(
      position => {
        this.geolocationPosition = position;
        this.currentLat = position.coords.latitude;
        this.currentLong = position.coords.longitude;
      });
  }

  getVenues() {

    this.recipeValue = this.recipes.nativeElement.value;
    this.placeValue = this.places.nativeElement.value;

    if (this.recipeValue !== null) {
      /**
       * Write code to get recipe
       */

      let foodApiUrl = this.recipeUrl + '&q='+ this.recipeValue;
      this._http.get(foodApiUrl).subscribe(res => {
        let recipes = res["hits"];
        recipes.map(ele => {
          let recipe = ele['recipe'];
          this.recipeList.push({
            name : recipe['label'],
            url : recipe['url'],
            icon : recipe['image']
          })
        })
      })
    }

    if (this.placeValue != null && this.placeValue !== '' && this.recipeValue != null && this.recipeValue !== '') {
      /**
       * Write code to get place
       */
      let placeUrl = this.venueUrl + "&near=" + this.placeValue + "&query="+this.recipeValue;
      this._http.get(placeUrl).subscribe(res => {
        let places = res["response"]["groups"][0]["items"];
        console.log(places)
        places.map(ele => {
          let place = ele['venue'];
          this.venueList.push({
            name : place.name,
            location : {
              formattedAddress: [place.location.address,place.location.city,place.location.country]
            }
          })
        })
      })
    }
  }
}
