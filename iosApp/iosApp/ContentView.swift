import SwiftUI
import shared


struct ContentView: View {
    let greet = Greeting().greet()
    let catsRepository: CatsRepository
    
    @ObservedObject var viewModel: CatsViewModel
    
    init(repository: CatsRepository = CatsRepository()) {
        self.catsRepository = repository
        self.viewModel = CatsViewModel(repository: catsRepository)
    }
    
    var body: some View {
        NavigationView {
            List(viewModel.breeds, id: \.id) { breed in
                NavigationLink(destination: BreedDetails(breed: breed, catsRepository: catsRepository)){
                    HStack {
                        AsyncImage(url: URL(string: breed.image?.url ?? "")){ image in
                            image
                                .resizable()
                                .aspectRatio(1, contentMode: .fit)
                                .frame(maxWidth: 80, alignment: .center)
                            
                        } placeholder: {
                            ProgressView().frame(maxWidth: 80, alignment: .center)
                        }
                            .clipShape(Circle(), style: FillStyle())
                        VStack {
                            Text(breed.name).font(Font.headline)
                            Text(breed.temperament).font(Font.subheadline)
                        }
                        
                    }
                    
                }
                .navigationBarTitle(Text("Cat breeds"))
                .navigationBarTitleDisplayMode(.large)
                
            }.task {
                //await viewModel.startObservingCatBreeds()
                viewModel.startObservingCatBreedsFlow() //flow
            }.environment(\.defaultMinListRowHeight, 100)
        }
    }
}

struct BreedDetails: View {
    @State var breed: BreedsListItem
    var catsRepository: CatsRepository
    @State var breedDetails: [ImageListItem] = [ImageListItem]()
    
    @ObservedObject var viewModel: DetailsCatsViewModel
    
    init(breed: BreedsListItem, catsRepository: CatsRepository) {
        self.breed = breed
        self.catsRepository = catsRepository
        self.viewModel = DetailsCatsViewModel(repository: catsRepository)
    }
    
    var body: some View {
        ScrollView {
            VStack {
                Text(breed.name).font(Font.largeTitle)
                Text(breed.description_).font(Font.body).foregroundColor(Color.gray)
                Divider()
                Text("Temperament").font(Font.headline)
                Text(breed.temperament).font(Font.body).foregroundColor(Color.gray)
                Divider()
                Group {
                    HStack {
                        Text("Affection").font(Font.body)
                        Spacer()
                        RatingView(maxRating: 5, rating: breed.affectionLevel?.doubleValue ?? 0)
                    }
                    HStack {
                        Text("Intelligence").font(Font.body)
                        Spacer()
                        RatingView(maxRating: 5, rating: breed.intelligence?.doubleValue ?? 0)
                    }
                    HStack {
                        Text("Energy").font(Font.body)
                        Spacer()
                        RatingView(maxRating: 5, rating: breed.energyLevel?.doubleValue ?? 0)
                    }
                    HStack {
                        Text("Child friendly").font(Font.body)
                        Spacer()
                        RatingView(maxRating: 5, rating: breed.childFriendly?.doubleValue ?? 0)
                    }
                    HStack {
                        Text("Dog friendly").font(Font.body)
                        Spacer()
                        RatingView(maxRating: 5, rating: breed.dogFriendly?.doubleValue ?? 0)
                    }
                }
                ScrollView(.horizontal){
                    HStack {
                        ForEach(breedDetails, id: \.id) { img in
                            AsyncImage(url: URL(string: img.url)) { image in
                                image
                                    .resizable()
                                    .aspectRatio(1, contentMode: .fit)
                                    .frame(maxWidth: 150, alignment: .center)
                                
                            } placeholder: {
                                ProgressView().frame(width: 150, alignment: .center).aspectRatio(1, contentMode: .fit)
                            }
                            .clipShape(RoundedRectangle(cornerRadius: 8.0), style: FillStyle())
                        }
                    }
                }
            }.task {
                await viewModel.getBreedDetails(id: breed.id)
                breedDetails = viewModel.breedDetails
            }.padding(EdgeInsets(top: 2, leading: 2, bottom: 2, trailing: 2))
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
