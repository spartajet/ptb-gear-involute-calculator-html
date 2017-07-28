package de.ptb.length.involute


import de.ptb.length.check.ICheckable

/**
 * @description
 * *
 * @create 2017-06-15 上午10:25
 * *
 * @email spartajet.guo@gmail.com
 */
interface IAnglePressure : IAngle, ICheckable {
    fun calculateValue(anglePressureNormal: IAngle, angleHelix: IAngle, diameterReference: DiameterReference, diameterBase: DiameterBase, teethNumber: TeethNumber, moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, moduleBasic: ModuleBasic)

    fun calculateContradiction(anglePressureNormal: IAngle, angleHelix: IAngle, diameterReference: DiameterReference, diameterBase: DiameterBase, teethNumber: TeethNumber, moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, moduleBasic: ModuleBasic)
}
