package 

import (
    ".Media/lib/context"
)

type MediaRepo struct {
    MediaList []model.Media
    datasetSize int
}

func NewMediaRepository() *MediaRepo {
    return &MediaRepo {
        MediaList: make([]model.Media, 0),
        datasetSize: 30,
    }
}

    func (r *MediaRepo) LocationsLocationIdMediaRecentGet() {

    }
    func (r *MediaRepo) Media1ShortcodeGet() {

    }
    func (r *MediaRepo) MediaMediaIdCommentsPost() {

    }
    func (r *MediaRepo) MediaMediaIdGet() {

    }
    func (r *MediaRepo) MediaMediaIdLikesGet() {

    }
    func (r *MediaRepo) MediaPopularGet() {

    }
    func (r *MediaRepo) MediaSearchGet() {

    }
