# WhoTouchesMyCountryKotlin


This is a copy of the swift version ( almost a copy ). Unfortunately I didn’t have time to read and watch to many guides other then exactly what I needed, 
so im not sure if I used the right guideline and conventions ( I think I used some but I dont know what I missed )

Few bad notes: 
- there is no error hendeling, or internet connection checks ( I didnt do it in the swift version eather )
- im not sure the folder structure is currect ( no idea what are the conventions )
- Im realy sorry for the networing state, I didint want to waste time on learing generics and writing nested classes just for one request.
- at some point the request didnt work on my home wifi but did on phone hotspot ( google said that I need to close and open emulator again and it worked,
no idea  what is the problem but I hope it wont heppen to you)


How the project works:
- first screen is CountryActivity, witch present a CountryListFragment ( writen in mvvm strucutre )
- topright menuButton gives sort options for the recycleView
- clicking a cell from the recycleView will open the second screen (countryBorderListFragment), on the same activity
- this second fragment containt another fragment with is the same CountryListFragment from the first screen 


Side notes: 
- eatch fragment have a diffrent view model, but the CountryListFragment can accept both because they implement the same interface
- the second fragment list have a textview inside it frameLayout,only if the list is empty then you can see the textview ( no idea if this is ok but it works )
- I used observe pattern (for the view models) and blocks, I thought about implementing delegate but I forgot 
- the app sopports orientation change ( the data is saved with the view model ), so there is no unnecessary reqeust calls.



This is my first time writhing in kotlin, hade to google evry line I did write, hope you enjoy 
