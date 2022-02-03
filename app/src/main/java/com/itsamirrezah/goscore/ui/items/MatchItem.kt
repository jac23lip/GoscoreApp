package com.itsamirrezah.goscore.ui.items

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itsamirrezah.goscore.R
import com.itsamirrezah.goscore.ui.model.MatchModel
import com.mikepenz.fastadapter.items.ModelAbstractItem

class MatchItem(
    private val match: MatchModel,
    private val onTeamInfo: OnTeamInfo,
    private val context: Context
) : ModelAbstractItem<MatchModel, MatchItem.MatchViewHolder>(match) {

    var isFlagLoaded = false
    override val layoutRes: Int
        get() = R.layout.match_item
    override val type: Int
        get() = R.id.FastAdapterMatchItem

    override fun getViewHolder(v: View): MatchViewHolder {
        return MatchViewHolder(v)
    }

    override fun bindView(holder: MatchViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)

        if (match.status == "SCHEDULED") {
            holder.lytMatchSchedule.visibility = View.VISIBLE
            holder.lytMatchScore.visibility = View.GONE
        } else {
            holder.lytMatchSchedule.visibility = View.GONE
            holder.lytMatchScore.visibility = View.VISIBLE
        }

        if (!isFlagLoaded)
            onTeamInfo.getTeamsFlag(match.homeTeam.id, match.awayTeam.id, object : OnResult {
                override fun onSuccess() {
                    isFlagLoaded = true
                }
            })

        Glide.with(context)
            .load(match.homeTeam.flagDrawable)
            .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_question_mark))
            .into(holder.ivHomeTeam)
        Glide.with(context)
            .load(match.awayTeam.flagDrawable)
            .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_question_mark))
            .into(holder.ivAwayTeam)

        holder.tvHomeTeam.text = match.homeTeam.name
        holder.tvAwayTeam.text = match.awayTeam.name
        holder.tvUtcDate.text = match.shortTime
        holder.tvMatchStatus.text = match.status.toLowerCase()
        holder.tvHomeTeamScore.text = match.homeTeamScore
        holder.tvAwayTeamScore.text = match.awayTeamScore
    }

    override fun unbindView(holder: MatchViewHolder) {
        super.unbindView(holder)

        holder.tvHomeTeam.text = ""
        holder.tvAwayTeam.text = ""
        holder.tvUtcDate.text = ""
        holder.tvMatchStatus.text = ""
        holder.tvHomeTeamScore.text = ""
        holder.tvAwayTeamScore.text = ""
        holder.lytMatchSchedule.visibility = View.GONE
        holder.lytMatchSchedule.visibility = View.GONE
        holder.ivAwayTeam.setImageDrawable(null)
        holder.ivHomeTeam.setImageDrawable(null)
    }

    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvHomeTeam: TextView = view.findViewById(R.id.tvHomeTeam)
        var tvAwayTeam: TextView = view.findViewById(R.id.tvAwayTeam)
        var tvUtcDate: TextView = view.findViewById(R.id.tvUtcDate)
        var lytMatchSchedule: View = view.findViewById(R.id.lytMatchSchedule)
        var lytMatchScore: View = view.findViewById(R.id.lytMatchScore)
        var tvHomeTeamScore: TextView = view.findViewById(R.id.tvHomeTeamScore)
        var tvAwayTeamScore: TextView = view.findViewById(R.id.tvAwayTeamScore)
        var tvMatchStatus: TextView = view.findViewById(R.id.tvMatchStatus)
        var ivHomeTeam: ImageView = view.findViewById(R.id.ivHomeTeam)
        var ivAwayTeam: ImageView = view.findViewById(R.id.ivAwayTeam)

    }
}

interface OnTeamInfo {
    fun getTeamsFlag(homeTeamId: Int, awayTeamId: Int, onResult: OnResult)
}

interface OnResult {
    fun onSuccess()
}