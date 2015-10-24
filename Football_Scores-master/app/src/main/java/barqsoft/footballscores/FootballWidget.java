package barqsoft.footballscores;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

import barqsoft.footballscores.service.FootballWidgetService;

/**
 * Implementation of App Widget functionality.
 */
public class FootballWidget extends AppWidgetProvider {

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {

            RemoteViews footballRemoteViews = new RemoteViews(context.getPackageName(), R.layout.football_widget);

            Intent footballServiceIntent = new Intent(context, FootballWidgetService.class);
            footballServiceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
            footballServiceIntent.setData(Uri.parse(footballServiceIntent.toUri(Intent.URI_INTENT_SCHEME)));

            footballRemoteViews.setRemoteAdapter(R.id.widget_collection_list, footballServiceIntent);


            Intent footballTemplate = new Intent(context, MainActivity.class);
            footballTemplate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            PendingIntent footballPendingIntent = PendingIntent.getActivity(context, 0, footballTemplate, PendingIntent.FLAG_UPDATE_CURRENT);
            footballRemoteViews.setPendingIntentTemplate(R.id.widget_collection_list, footballPendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, footballRemoteViews);

        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
}

