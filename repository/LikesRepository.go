package 

import (
    ".Likes/lib/context"
)

type LikesRepo struct {
    LikesList []model.Likes
    datasetSize int
}

func NewLikesRepository() *LikesRepo {
    return &LikesRepo {
        LikesList: make([]model.Likes, 0),
        datasetSize: 30,
    }
}

    func (r *LikesRepo) MediaMediaIdLikesDelete() {

    }
    func (r *LikesRepo) MediaMediaIdLikesGet() {

    }
    func (r *LikesRepo) MediaMediaIdLikesPost() {

    }
