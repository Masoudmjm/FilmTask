package com.masoudjafari.filmnet.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(

	@field:SerializedName("data")
	var data: List<DataItem?>? = null,

	@field:SerializedName("meta")
	val meta: Meta? = null
)

data class Meta(

	@field:SerializedName("total_items_count")
	val totalItemsCount: Int? = null,

	@field:SerializedName("remaining_items_count")
	val remainingItemsCount: Int? = null,

	@field:SerializedName("operation_result")
	val operationResult: String? = null,

	@field:SerializedName("next_url")
	val nextUrl: String? = null,

	@field:SerializedName("display_message")
	val displayMessage: String? = null,

	@field:SerializedName("operation_result_code")
	val operationResultCode: Int? = null,

	@field:SerializedName("server_date_time")
	val serverDateTime: String? = null
)

data class PosterImage(

	@field:SerializedName("path")
	val path: String? = null
)

data class ThumbnailImage(

	@field:SerializedName("path")
	val path: String? = null
)

data class AlterCoverImage(

	@field:SerializedName("path")
	val path: String? = null
)

data class CoverImage(

	@field:SerializedName("path")
	val path: String? = null
)

data class ItemsItem(

	@field:SerializedName("title")
	val title: String? = null
)

data class DataItem(

	@field:SerializedName("summary")
	val summary: String? = null,

	@field:SerializedName("conditional_flag")
	val conditionalFlag: String? = null,

	@field:SerializedName("flag")
	val flag: String? = null,

	@field:SerializedName("year")
	val year: Int? = null,

	@field:SerializedName("page_title")
	val pageTitle: String? = null,

	@field:SerializedName("imdb_rank_percent")
	val imdbRankPercent: Int? = null,

	@field:SerializedName("alter_cover_image")
	val alterCoverImage: AlterCoverImage? = null,

	@field:SerializedName("short_id")
	val shortId: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("logo_image")
	val logoImage: LogoImage? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("duration")
	val duration: String? = null,

	@field:SerializedName("rate")
	val rate: Double? = null,

	@field:SerializedName("original_name")
	val originalName: String? = null,

	@field:SerializedName("cover_image")
	val coverImage: CoverImage? = null,

	@field:SerializedName("categories")
	val categories: List<CategoriesItem?>? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("age_restriction")
	val ageRestriction: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("poster_image")
	val posterImage: PosterImage? = null,

	@field:SerializedName("episode")
	val episode: Int? = null,

	@field:SerializedName("thumbnail_image")
	val thumbnailImage: ThumbnailImage? = null,

	@field:SerializedName("season")
	val season: Int? = null
)

data class CategoriesItem(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)

data class LogoImage(

	@field:SerializedName("path")
	val path: String? = null
)
