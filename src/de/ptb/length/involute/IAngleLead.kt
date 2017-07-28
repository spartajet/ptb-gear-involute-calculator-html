package de.ptb.length.involute


import de.ptb.length.check.ICheckable

/**
 * @description
 * *
 * @create 2017-06-15 上午10:24
 * *
 * @email spartajet.guo@gmail.com
 */
interface IAngleLead : IAngle, ICheckable {
    fun calculateContradiction(moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, moduleAxial: ModuleAxial, angleHelix: IAngle)

    fun calculateValue(moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, moduleAxial: ModuleAxial, angleHelix: IAngle)
}
