//
//  RatingView.swift
//  iosApp
//
//  Created by Aljosa Koren on 21/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

public enum StarRounding: Int {
    case roundToHalfStar = 0
    case ceilToHalfStar = 1
    case floorToHalfStar = 2
    case roundToFullStar = 3
    case ceilToFullStar = 4
    case floorToFullStar = 5
}

struct StarView: View {
    let isFilled: Bool
    let color: Color
    
    private let fullStarImage: Image = Image(systemName: "star.fill")
    private let halfStarImage: Image = Image(systemName: "star.lefthalf.fill")
    private let emptyStarImage: Image = Image(systemName: "star")
    
    var body: some View {
        isFilled ? fullStarImage : emptyStarImage
    }
}

struct RatingView: View {
    let maxRating: Int
    @State var rating: Double
    let starColor: Color
    let starRounding: StarRounding
    let size: CGFloat
    
    private let fullStarImage: Image = Image(systemName: "star.fill")
    private let halfStarImage: Image = Image(systemName: "star.lefthalf.fill")
    private let emptyStarImage: Image = Image(systemName: "star")
    
    @State private var selectedStar: Int?
    
    init(maxRating: Int, rating: Double = 0, starColor: Color = .blue, starRounding: StarRounding = .floorToFullStar, size: CGFloat = 20) {
        self.maxRating = maxRating
        self.rating = rating
        self.starColor = starColor
        self.starRounding = starRounding
        self.size = size
    }
    
    var body: some View {
        HStack {
            ForEach(1...maxRating, id: \.self) { index in
                starImageView(index: index)
                    .foregroundColor(starColor)
                    .onTapGesture {
                        self.rating = Double(index)
                        withAnimation(.easeInOut(duration: 0.5)) {
                            selectedStar = index
                        }
                        DispatchQueue.main.asyncAfter(deadline: .now() + 0.5) {
                            selectedStar = nil
                        }
                    }
            }
        }
    }
    
    func starImageView(index: Int) -> some View {
        let iFloat = Double(index)
        let image: Image
        switch starRounding {
        case .roundToHalfStar:
            image = rating >= iFloat-0.25 ? fullStarImage :
                   (rating >= iFloat-0.75 ? halfStarImage : emptyStarImage)
        case .ceilToHalfStar:
            image = rating > iFloat-0.5 ? fullStarImage :
                   (rating > iFloat-1 ? halfStarImage : emptyStarImage)
        case .floorToHalfStar:
            image = rating >= iFloat ? fullStarImage :
                   (rating >= iFloat-0.5 ? halfStarImage : emptyStarImage)
        case .roundToFullStar:
            image = rating >= iFloat-0.5 ? fullStarImage : emptyStarImage
        case .ceilToFullStar:
            image = rating > iFloat-1 ? fullStarImage : emptyStarImage
        case .floorToFullStar:
            image = rating >= iFloat ? fullStarImage : emptyStarImage
        }
        return image
            .resizable()
            .aspectRatio(contentMode: .fit)
            .frame(width: size, height: size)
            .overlay(
                selectedStar == index ? Circle()
                    .strokeBorder(starColor, lineWidth: 4)
                    .scaleEffect(1.5)
                    .opacity(0)
                    .animation(Animation.easeInOut(duration: 1)
                        .repeatForever(autoreverses: false), value: 1) : nil
            )
    }
    
    /*struct RatingView_Previews: PreviewProvider {
        static var previews: some View {
            Group {
                RatingView(maxRating: 5, rating: 3.5)
                    .previewLayout(.sizeThatFits)
                
                RatingView(maxRating: 10, rating: .constant(8), starColor: .yellow, starRounding: .ceilToHalfStar, size: 30)
                    .previewLayout(.sizeThatFits)
                
                RatingView(maxRating: 3, rating: .constant(2), starColor: .green, starRounding: .roundToFullStar, size: 50)
                    .previewLayout(.sizeThatFits)
            }
        }
    }*/

}
