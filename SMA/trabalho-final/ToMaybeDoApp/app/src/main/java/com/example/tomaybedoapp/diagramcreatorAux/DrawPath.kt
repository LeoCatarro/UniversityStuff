package com.example.tomaybedoapp.diagramcreatorAux

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.tomaybedoapp.diagramcreatorAux.PaintPath
import java.lang.Math.abs

open class DrawPath @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): View(context, attrs, defStyleAttr)
{
    private var paint : Paint? = null
    private var path: Path? = null
    private var mPath : Path? = null
    private var pathList = ArrayList<PaintPath>()
    private var undoPathList = ArrayList<PaintPath>()
    private var mX : Float? = null
    private var mY : Float? = null
    private var touchTolerance : Float = 4f

    //Initialize paint path variable
    init {
        paint = Paint()
        paint!!.color = Color.BLACK
        paint!!.strokeWidth = 10f
        paint!!.style = Paint.Style.STROKE
        paint!!.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        if (pathList.size > 0) {
            for (path: PaintPath in pathList)
                canvas!!.drawPath(path.path, paint!!)
        }
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val xPosition : Float = event!!.x
        val yPosition : Float = event.y

        when(event.action)
        {
            MotionEvent.ACTION_DOWN -> {
                touchStart(xPosition, yPosition)
                invalidate()
                //path!!.moveTo(xPosition, yPosition)
            }
            MotionEvent.ACTION_MOVE -> {
                touchMove(xPosition, yPosition)
                invalidate()
                //path!!.lineTo(xPosition, yPosition)
            }
            MotionEvent.ACTION_UP -> {
                touchUp(xPosition, yPosition)
                invalidate()
            }
            else -> {

            }
        }
        invalidate()
        return true
    }

    private fun touchStart(xPosition: Float, yPosition: Float) {
        mPath = Path()
            val paintPath = PaintPath(mPath!!)
            pathList.add(paintPath)
            mPath!!.reset()
            mPath!!.moveTo(xPosition, yPosition)
            mX = xPosition
            mY = yPosition
    }

    private fun touchMove(xPosition: Float, yPosition: Float) {
        val dX : Float = abs(xPosition - mX!!)
        val dY : Float = abs(yPosition - mY!!)

        if(dX >= touchTolerance || dY >= touchTolerance)
        {
            mPath?.quadTo(mX!!, mY!!, (xPosition+mX!!)/2, (yPosition+mY!!)/2)
            mX = xPosition
            mY = yPosition
        }
    }

    private fun touchUp(xPosition: Float, yPosition: Float) {
        mPath!!.lineTo(mX!!, mY!!)
    }

    //Function to handle the undo functionality
    fun setUndo()
    {
        var listSize = pathList.size

        if(listSize > 0)
        {
            undoPathList.add(pathList[listSize-1])
            pathList.removeAt(listSize-1)
            invalidate()
        }
    }

    //Function to handle the reundo functionality
    fun setRedo()
    {
        var listSize = undoPathList.size

        if(listSize > 0)
        {
            pathList.add(undoPathList[listSize-1])
            undoPathList.removeAt(listSize-1)
            invalidate()
        }

    }

    //Function to clear all paths in page
    fun setDeleteAll() {
        var listSize = pathList.size

        if(listSize > 0)
        {
            pathList.clear()
            undoPathList.clear()
            invalidate()
        }
    }

}