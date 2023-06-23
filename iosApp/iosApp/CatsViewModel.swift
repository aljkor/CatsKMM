//
//  CatsViewModel.swift
//  iosApp
//
//  Created by Aljosa Koren on 09/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import Combine
import KMPNativeCoroutinesAsync

@MainActor
class CatsViewModel: ObservableObject {
    @Published var breeds = [BreedsListItem]()
    
    private let repository: CatsRepository
    init(repository: CatsRepository){
        self.repository = repository
    }
    
    func startObservingCatBreeds() async {
        do {
            let brds = try await repository.getAllBreeds()
            self.breeds = brds
        } catch {
            print("Error hapnd")
        }
    }
    
    func startObservingCatBreedsFlow(){
        repository.getAllBreedsAsFlow().watch { [unowned self] nullableArray in
            guard let list = nullableArray?.compactMap({ $0 as? BreedsListItem }) else {
                return
            }
            self.breeds = list
            
            /*print("list length")
            print(list.count)
            print(Date().timeIntervalSinceReferenceDate)*/
        }
    }
}
