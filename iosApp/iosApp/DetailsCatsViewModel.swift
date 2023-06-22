//
//  DetailsCatsViewModel.swift
//  iosApp
//
//  Created by Aljosa Koren on 09/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor
class DetailsCatsViewModel: ObservableObject {
    @Published var breedDetails = [ImageListItem]()
    
    private let repository: CatsRepository
    init(repository: CatsRepository){
        self.repository = repository
    }
    
    func getBreedDetails(id: String) async {
        do {
            let bred = try await repository.getBreedDetails(breedId: id)
            self.breedDetails = bred
        } catch {
            print("Breed details error.")
        }
    }
}
